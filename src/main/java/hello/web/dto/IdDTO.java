package hello.web.dto;


/**
 * Class for transferring {@link hello.entity.User}'s id
 */
public class IdDTO {

    private Long id;


    /**
     * Constructor for {@link IdDTO}
     *
     * @param id for {@link hello.entity.User}
     */
    public IdDTO(Long id) {
        this.id = id;
    }

    /**
     * @return {@link hello.entity.User}'s id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setting id to {@link IdDTO}
     *
     * @param id for {@link hello.entity.User}
     */
    public void setId(Long id) {
        this.id = id;
    }
}
