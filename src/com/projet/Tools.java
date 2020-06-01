package com.projet;

import com.projet.Question.Question;
import com.projet.Question.Type.QCM;
import com.projet.Question.Type.RC;
import com.projet.Question.Type.VF;

public class Tools {
    public Tools() {
    }

    public static String getQuestionType(Question question){
        switch (question.getEnonce().getClass().getName()){
            case "com.projet.Question.Type.QCM":{
                return "QCM";
            }
            case "com.projet.Question.Type.VF":{
                return "VF";
            }
            case "com.projet.Question.Type.RC":{
                return "RC";
            }
            default:
                break;
        }
        return "";
    }

}
