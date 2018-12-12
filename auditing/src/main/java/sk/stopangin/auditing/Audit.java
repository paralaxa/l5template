package sk.stopangin.auditing;

/**
 * Created by martin.cuchran on 12/11/2018.
 */
public class Audit {
    private Long id;
    private String auditedEntityClassname;
    private Long auditedEntityId;

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

    public Long getAuditedEntityId() {
        return auditedEntityId;
    }

    public void setAuditedEntityId(Long auditedEntityId) {
        this.auditedEntityId = auditedEntityId;
    }
}
