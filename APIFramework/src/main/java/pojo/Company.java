package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "name",
    "catchPhrase",
    "bs"
})

public class Company {
	
	@JsonProperty("name")
    private String name;
    @JsonProperty("catchPhrase")
    private String catchPhrase;
    @JsonProperty("bs")
    private String bs;
    

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("catchPhrase")
    public String getCatchPhrase() {
        return catchPhrase;
    }

    @JsonProperty("catchPhrase")
    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    @JsonProperty("bs")
    public String getBs() {
        return bs;
    }

    @JsonProperty("bs")
    public void setBs(String bs) {
        this.bs = bs;
    }


}
