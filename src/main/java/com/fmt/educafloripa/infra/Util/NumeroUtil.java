package com.fmt.educafloripa.infra.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumeroUtil {

    public static float adequarFloat (Float numero) {
        BigDecimal bd = new BigDecimal(numero).setScale(2, RoundingMode.HALF_EVEN);
        return Float.parseFloat(bd.toString());
    }

    public static boolean eNumero(String texto) {
        for (char c : texto.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
