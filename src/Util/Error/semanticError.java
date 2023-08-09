package Util.Error;

import Util.position;

public class semanticError extends error {
    public semanticError(String msg, position pos) {
        super("semanticError: " + msg, pos);
    }
}
