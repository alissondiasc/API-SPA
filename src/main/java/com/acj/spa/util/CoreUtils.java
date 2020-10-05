package com.acj.spa.util;

import java.util.Arrays;
import java.util.List;

public abstract class CoreUtils {
    public static String retornarPrimeiroCampoRegex(String parametro){
        List<String> nomes = Arrays.asList(parametro.split(" "));
        return "(?i).*".concat(nomes.get(0).concat(".*"));
    }

    public static String retornarRegexParametroPesquisa(String parametro){
        List<String> nomes = Arrays.asList(parametro.split(" "));
        StringBuilder regex = new StringBuilder("");
        for (String name : nomes) {
            regex = regex.append("(?i)(?=.*").append(name).append(")");
        }
        return regex.toString();
    }
}
