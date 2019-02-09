package com.example.mathhack;

import java.util.Random;

public class Addition implements MathProblems {
    private int _answer;
    private int[] _result;

    public Addition(int min, int max) {
        _result = CreateProblem(min, max);
        System.out.println("Hey George! We have a problem for you today!!!!");
        _answer = _result[2];
    }

    public String GetEquation() {
        return ("What is " + _result[0] + " + " + _result[1] + " = ?");
    }

    public boolean CheckAnswer(int answer) {
        if (answer == _answer) {
            return true;
        }
        return false;
    }

    public int GetAnswer() {
        return _answer;
    }

    public String GetReturnMessage(int returnCode) {
        switch (returnCode)
        {
            case 0:
                return "That's awesome! You got the correct answer!";
            case 1:
                return "YOU ARE SO CLOSE!! Try again";
            case 2:
                return "Keep going you are on fire! Try a little lower.";
            case 3:
                return "Sorry your answer was just a little too high... Try again!";
            case 4:
                return "Keep going you are on fire! Try a little higher.";
            case 5:
                return "Sorry your answer was just a little too low... Try again!";
            case -1:
                return "Use a correct return code.";
        }
        return "Use a correct return code.";
    }

    public int[] CreateProblem(int min, int max) {
        int[] result = new int[3];

        Random randomNum = new Random();

        result[0] = min + randomNum.nextInt(max);
        result[1] = min + randomNum.nextInt(max);
        result[2] = result[0] + result[1];
        return result;
    }

}
