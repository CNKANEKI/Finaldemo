package org.example.finaldemo.Controller;

import org.example.finaldemo.Entity.Manage;
import org.example.finaldemo.Entity.Score;
import org.example.finaldemo.Entity.ScoreId;
import org.example.finaldemo.Service.ScoreService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/showStuScore/{id}")
    public String showStuScore(@PathVariable(value = "id") long sid,
                               Model model) {
        List<Score> Scorelist = scoreService.getScoreBysid(sid);
        model.addAttribute("sid", sid);
        model.addAttribute("ScoreList", Scorelist);
        return "scoreform";
    }

    @PostMapping("/saveScore")
    public String saveScore(@ModelAttribute("score") Score score) {
        scoreService.saveScore(score);
        long id = score.getId().getSid();
        return "redirect:/showStuScore/" + id;
    }

    @GetMapping("/showScoreAdd/{id}")
    public String showScoreAdd(@PathVariable(value = "id") long sid,
                               Model model) {
        Score score = new Score();
        ScoreId scoreId = new ScoreId();
        scoreId.setSid(sid);
        score.setId(scoreId);
        model.addAttribute("score", score);
        return "new_score";
    }

    @GetMapping("/showScoreUpdate/{id}/{course}")
    public String showScoreUpdate(@PathVariable(value = "id") long sid,
                                  @PathVariable(value = "course") String course,
                                  Model model){
            Score score = scoreService.getScoreBySidAndCourse(sid, course);
            model.addAttribute("score", score);
            return "update_score";
    }

    @GetMapping("/deleteScore/{id}/{course}")
    public String deleteStu(@PathVariable(value = "id") long sid,
                            @PathVariable(value = "course") String course) {
        this.scoreService.deleteScoreBySidAndCourse(sid, course);
        return "redirect:/showStuScore/" + sid;
    }

    @GetMapping("/showStuScoreInHome/{id}")
    public String showStuScoreInHome(@PathVariable(value = "id") long sid,
                               Model model) {
        List<Score> Scorelist = scoreService.getScoreBysid(sid);
        model.addAttribute("sid", sid);
        model.addAttribute("ScoreList", Scorelist);
        return "HomeForScore";
    }

}
