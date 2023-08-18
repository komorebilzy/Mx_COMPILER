package Util.Error;

import Util.position;

public class irError extends error{
    public irError(String msg, position pos) {
        super("irError: " + msg, pos);
    }

}
