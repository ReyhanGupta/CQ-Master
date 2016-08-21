package com.devesh.intern.Tables;

/**
 * Created by devesh on 21/8/16.
 */
public class SQL_Commands extends Basic_Req{

    public static final String TABLE_NAME = "Offline_Story";

    public static final String TABLE_CREATE_CMD = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME
            +LBR
            +Columns.ID + TYPE_INT_PK + COMMA
            +Columns.NAME + TYPE_TEXT + COMMA
            +Columns.GENRE + TYPE_TEXT + COMMA
            +Columns.SYNOPSIS + TYPE_TEXT + COMMA
            +Columns.STORY + TYPE_TEXT +COMMA
            +Columns.DATE + TYPE_TEXT + COMMA
            +Columns.TIME + TYPE_TEXT
            +RBR + ";" ;

    public interface Columns{
        String ID = "id";
        String STORY = "story";
        String GENRE = "genre";
        String SYNOPSIS = "synopsis";
        String NAME = "title";
        String DATE = "date";
        String TIME = "time";
    }



}
