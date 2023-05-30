package ru.job4j.hmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double tmp = 0;
        int count = 0;
        for (Pupil pup : pupils) {
            for (Subject sub : pup.subjects()) {
                tmp += sub.score();
                count++;
            }
        }
        return tmp / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        double tmp = 0;
        int count = 0;
        for (Pupil pup : pupils) {
            for (Subject sub : pup.subjects()) {
                tmp += sub.score();
                count++;
            }
            labels.add(new Label(pup.name(), tmp / count));
            tmp = 0;
            count = 0;
        }
        return labels;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        Map<String, Integer> map = new LinkedHashMap<>();
        int count = 0;
        for (Pupil pup : pupils) {
            count++;
            for (Subject sub : pup.subjects()) {
                map.merge(sub.name(), sub.score(), (oldScore, newScore) -> oldScore + sub.score());
            }
        }
        for (String key : map.keySet()) {
            Double value = Double.valueOf(map.get(key));
            labels.add(new Label(key, value / count));
        }
        return labels;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        double tmp = 0;
        for (Pupil pup : pupils) {
            for (Subject sub : pup.subjects()) {
                tmp += sub.score();
            }
            labels.add(new Label(pup.name(), tmp));
            tmp = 0;
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Pupil pup : pupils) {
            for (Subject sub : pup.subjects()) {
                map.merge(sub.name(), sub.score(), (oldScore, newScore) -> oldScore + sub.score());
            }
        }
        for (String key : map.keySet()) {
            Double value = Double.valueOf(map.get(key));
            labels.add(new Label(key, value));
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }
}
