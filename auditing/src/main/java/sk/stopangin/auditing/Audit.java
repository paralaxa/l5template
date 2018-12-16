package sk.stopangin.auditing;

/**
 * Created by martin.cuchran on 12/11/2018.
 */
public class Audit {
    private Long id;
    private String auditedEntityClassname;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuditedEntityClassname() {
        return auditedEntityClassname;
    }

    public void setAuditedEntityClassname(String auditedEntityClassname) {
        this.auditedEntityClassname = auditedEntityClassname;
    }
}
