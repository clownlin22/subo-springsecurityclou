package cn.superbio.spbbase.authcenter.VO;

import java.io.Serializable;

/**
 * 角色
 * @author lxy
 */


public class SysRoleVO implements Serializable{

    private static final long serialVersionUID = 1665451156458509057L;
    private Long id;
    private String code;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
