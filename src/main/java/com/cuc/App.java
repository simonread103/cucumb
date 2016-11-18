package com.cuc;

import java.io.IOException;
import java.util.Optional;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        TitleLookupService service = new TitleLookupService();

        try {
            Optional<String> title = service.getTitleById("5");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

