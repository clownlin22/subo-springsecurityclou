package cn.superbio.spbbase.authcenter.VO;


import java.io.Serializable;

/**
 * @author lxy
 */

public class AppUserVO   implements Serializable {

    private static final long serialVersionUID = -3429933529387279079L;
    private Long id;
    private String name;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
