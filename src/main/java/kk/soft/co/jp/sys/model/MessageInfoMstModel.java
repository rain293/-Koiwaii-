package kk.soft.co.jp.sys.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class MessageInfoMstModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private int id;

    /**
     * msg
     */
    private String msg;

    /**
     * sender
     */
    private String sender;
    
    
    private String name;
    private String email;
    private String password;
    private String gender;
    private String marriage;
    private String privacy;
    private String magazine;
    private String point;


    public MessageInfoMstModel() {
    }


}
