package com.skytec.service.duel;

import com.skytec.bean.*;
import com.skytec.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.*;

@Service
public class DuelServiceImpl implements DuelService {

    private final SessionsRepository sessionsRepository;
    private final UserRepository userRepository;
    private final ReadyToDuelRepository readyToDuelRepository;
    private final DuelsRepository duelsRepository;
    private final CurrentDuelsValuesRepository currentDuelsValuesRepository;
    private final DamageDuelRepository damageDuelRepository;

    private Date currentDate = new Date();

    @Autowired
    public DuelServiceImpl(SessionsRepository sessionsRepository,
                           UserRepository userRepository,
                           ReadyToDuelRepository readyToDuelRepository,
                           DuelsRepository duelsRepository,
                           CurrentDuelsValuesRepository currentDuelsValuesRepository,
                           DamageDuelRepository damageDuelRepository) {
        this.sessionsRepository = sessionsRepository;
        this.userRepository = userRepository;
        this.readyToDuelRepository = readyToDuelRepository;
        this.duelsRepository = duelsRepository;
        this.currentDuelsValuesRepository = currentDuelsValuesRepository;
        this.damageDuelRepository = damageDuelRepository;
    }

    @Override
    public String isDuel(Long sessionId, ModelMap modelMap) {

        Session session = sessionsRepository.findBySessionId(sessionId);
        User user = userRepository.findById(session.getUserId());

        if (readyToDuelRepository.findByUserId(user.getId()) == null) {
            readyToDuelRepository.save(ReadyToDuel.builder()
                    .userId(user.getId())
                    .build());
        }

        modelMap.put("yourLogin", user.getLogin());
        modelMap.put("yourHealth", user.getHealth());
        modelMap.put("yourHealthMax", user.getHealth());
        modelMap.put("yourDamage", user.getDamage());

        return "duel";
    }

    private ReadyToDuel getReadyToDuel(User user) {

        List<ReadyToDuel> listRTD = (List<ReadyToDuel>) readyToDuelRepository.findAll();
        System.out.println(listRTD);
        Long[] arrayUserIdsRTD = new Long[(listRTD.size()-1 < 0) ? 0 : listRTD.size()-1];

        if (arrayUserIdsRTD.length <= 0) return null;
        System.out.println(arrayUserIdsRTD.length);

        for (int i = 0, k = 0; i < listRTD.size(); i++, k++) {
            if (listRTD.get(i).getUserId().equals(user.getId())) {
                k--;
            } else {
                arrayUserIdsRTD[k] = listRTD.get(i).getUserId();
            }
        }

        Random random = new Random();
        Integer randomUserId = random.nextInt(arrayUserIdsRTD.length);
        System.out.println(randomUserId);
        return listRTD.get(randomUserId);
    }

    @Override
    public String searchEnemy(Long sessionId, ModelMap modelMap) {

        System.out.println("Search enemy");

        System.out.println(modelMap);

        Session session = sessionsRepository.findBySessionId(sessionId);
        User user = userRepository.findById(session.getUserId());

        System.out.println("User id: " + user.getId());

        ReadyToDuel readyToDuelEnemy = getReadyToDuel(user);

        System.out.println(readyToDuelEnemy);

        modelMap.put("yourLogin", user.getLogin());
        modelMap.put("yourHealth", user.getHealth());
        modelMap.put("youHealthMax", user.getHealth());
        modelMap.put("yourDamage", user.getDamage());

        if (readyToDuelEnemy == null) {
            if (readyToDuelRepository.findByUserId(user.getId()) != null) {
                addEnemyInfo(modelMap, user);
            } else {
                if (currentDuelsValuesRepository.findByUserId(user.getId()) != null) {
                    addEnemyInfo(modelMap, user);
                } else {
                    modelMap.put("labelWaitValue", "Соперник не найден. Возобновите поиск позже");
                }
            }
        } else {
            if (readyToDuelRepository.findByUserId(user.getId()) != null) {
                User userEnemy = userRepository.findById(readyToDuelEnemy.getUserId());

                duelsRepository.save(Duels.builder()
                        .date(currentDate)
                        .userOneId(user.getId())
                        .userTwoId(userEnemy.getId())
                        .build());

                if (readyToDuelRepository.findByUserId(user.getId()) != null) {
                    readyToDuelRepository.delete(
                            readyToDuelRepository.findByUserId(user.getId())
                    );
                }

                System.out.println(user.getId());
                System.out.println(userEnemy.getId());
                if (readyToDuelRepository.findByUserId(userEnemy.getId()) != null) {
                    readyToDuelRepository.delete(
                            readyToDuelRepository.findByUserId(userEnemy.getId())
                    );
                }

                currentDuelsValuesRepository.save(CurrentDuelsValues.builder()
                        .duelId(duelsRepository.findByDate(currentDate).getId())
                        .health(user.getHealth())
                        .userId(user.getId())
                        .build());

                currentDuelsValuesRepository.save(CurrentDuelsValues.builder()
                        .duelId(duelsRepository.findByDate(currentDate).getId())
                        .health(userEnemy.getHealth())
                        .userId(userEnemy.getId())
                        .build());

                modelMap.put("enemyLogin", userEnemy.getLogin());
                modelMap.put("enemyHealth", userEnemy.getHealth());
                modelMap.put("emenyHealthMax", userEnemy.getHealth());
                modelMap.put("enemyDamage", userEnemy.getDamage());
                modelMap.put("labelWaitValue", "Соперник найден");
            } else {
                addEnemyInfo(modelMap, user);
            }
        }
        return "duel";
    }

    @Override
    public String attack(Long sessionId, ModelMap modelMap) {
        Session session = sessionsRepository.findBySessionId(sessionId);
        User user = userRepository.findById(session.getUserId());

        CurrentDuelsValues userValues = currentDuelsValuesRepository.findByUserId(user.getId());
        Duels duel = duelsRepository.findById(userValues.getDuelId());
        User userEnemy = userRepository.findById(
                duelsRepository.findById(userValues.getDuelId()).getUserTwoId());
        CurrentDuelsValues enemyValues = currentDuelsValuesRepository.findByUserId(userEnemy.getId());

        modelMap.put("yourHealth", userValues.getHealth());
        modelMap.put("yourHealthMax", user.getHealth());
        modelMap.put("yourDamage", user.getDamage());
        modelMap.put("enemyHealth", enemyValues.getHealth()-user.getDamage());
        modelMap.put("enemyHealthMax", userEnemy.getHealth());
        modelMap.put("enemyDamage", userEnemy.getDamage());
        modelMap.put("labelWaitValue", "Идет бой");

        CurrentDuelsValues currentDuelsValuesEnemy = currentDuelsValuesRepository.findByUserId(userEnemy.getId());
        currentDuelsValuesRepository.delete(currentDuelsValuesEnemy);
        currentDuelsValuesRepository.save(CurrentDuelsValues.builder()
                .duelId(currentDuelsValuesEnemy.getDuelId())
                .health((currentDuelsValuesEnemy.getHealth()-user.getDamage() < 0)
                        ? 0 : currentDuelsValuesEnemy.getHealth()-user.getDamage())
                .userId(currentDuelsValuesEnemy.getUserId())
                .build());

        damageDuelRepository.save(DamageDuel.builder()
                .duelId(duel.getId())
                .damagerId(user.getId())
                .damage(user.getDamage())
                .build());

        StringBuffer sb = new StringBuffer();
        sb.append(user.getLogin())
                .append(" ударил ")
                .append(userEnemy.getLogin())
                .append(" на ")
                .append(user.getDamage())
                .append(" урона");
        modelMap.put("textAreaValue", sb);

        User winner;
        User looser;
        List<CurrentDuelsValues> currentDuelsValues = (List<CurrentDuelsValues>)
                currentDuelsValuesRepository.findByDuelId(currentDuelsValuesEnemy.getDuelId());
        if (currentDuelsValues.get(0).getHealth() <= 0) {
            winner = userRepository.findById(currentDuelsValues.get(1).getUserId());
            looser = userRepository.findById(currentDuelsValues.get(0).getUserId());
            return saveAfterDuel(winner, looser);
        } else if (currentDuelsValues.get(1).getHealth() <= 0){
            winner = userRepository.findById(currentDuelsValues.get(0).getUserId());
            looser = userRepository.findById(currentDuelsValues.get(1).getUserId());
            return saveAfterDuel(winner, looser);
        }

        return "redirect:/duel?long=" + sessionId;
    }

    private void addEnemyInfo(ModelMap modelMap, User user) {
        Duels duel = duelsRepository.findByUserTwoId(user.getId());
        User userEnemy = userRepository.findById(duel.getUserOneId());

        modelMap.put("enemyLogin", userEnemy.getLogin());
        modelMap.put("enemyHealth", userEnemy.getHealth());
        modelMap.put("emenyHealthMax", userEnemy.getHealth());
        modelMap.put("enemyDamage", userEnemy.getDamage());
        modelMap.put("labelWaitValue", "Соперник найден.");
    }

    private String saveAfterDuel(User winner, User looser) {
        userRepository.delete(winner);
        userRepository.delete(looser);
        winner.setHealth(winner.getHealth()+1);
        winner.setDamage(winner.getDamage()+1);
        winner.setRating(winner.getRating()+1);
        looser.setHealth(looser.getHealth()+1);
        looser.setDamage(looser.getDamage()+1);
        looser.setRating(looser.getRating()-1);
        userRepository.save(winner);
        userRepository.save(looser);

        currentDuelsValuesRepository.delete
                (currentDuelsValuesRepository.findByDuelId
                        (duelsRepository.findByDate(currentDate).getId()));

        return "info";
    }
}
