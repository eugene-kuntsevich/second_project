package hello.web.request;

import hello.entity.User;

import javax.validation.constraints.NotNull;

public class UserFilterByAgeRequest {

    @NotNull
    private Integer min;

    @NotNull
    private Integer max;

    /**
     * constructor for {@link UserFilterByAgeRequest}
     */
    public UserFilterByAgeRequest() {
    }

    /**
     * @return minimal value for {@link User}'s age
     */
    public Integer getMin() {
        return min;
    }

    /**
     * @param min
     */
    public void setMin(Integer min) {
        this.min = min;
    }

    /**
     * @return maximal value for {@link User}'s age
     */
    public Integer getMax() {
        return max;
    }

    /**
     * @param max
     */
    public void setMax(Integer max) {
        this.max = max;
    }
}
