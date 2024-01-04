package com.jaba.vgl.exceptions;

import java.io.IOException;

public class NewsItemsNotFoundException extends RuntimeException{
    public NewsItemsNotFoundException(String message){
        super(message);
    }

    public NewsItemsNotFoundException(String message, IOException e) {
        super(message, e);
    }
}
