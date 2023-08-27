package Util.Error;

import Util.position;

public class codgenError extends error{
    public codgenError(String msg, position pos) {
        super("codgenError: " + msg, pos);
    }

}

