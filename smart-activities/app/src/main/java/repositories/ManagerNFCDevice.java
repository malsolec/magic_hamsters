package repositories;

/**
 * Created by mikolevy on 22.04.16.
 */
public class ManagerNFCDevice {

    private Long id;
    private Integer deviceId;
    private Long kidActivityId;

    public ManagerNFCDevice(Long id, Integer deviceId, Long kidActivityId) {
        this.id = id;
        this.deviceId = deviceId;
        this.kidActivityId = kidActivityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Long getKidActivityId() {
        return kidActivityId;
    }

    public void setKidActivityId(Long kidActivityId) {
        this.kidActivityId = kidActivityId;
    }
}
