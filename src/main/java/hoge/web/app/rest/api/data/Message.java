package hoge.web.app.rest.api.data;

/**
 * Created by s_kozake on 2015/03/07.
 */
public class Message {
    private String developerMessage;
    private String userMessage;
    private String errorCode;
    private String moreInfo;

    public Message() {

    }

    public static class Builder {
        Message msg = new Message();

        public Builder setDeveloperMessage(String developerMessage) {
            msg.developerMessage = developerMessage;
            return this;
        }

        public Builder setUserMessage(String userMessage) {
            msg.userMessage = userMessage;
            return this;
        }

        public Builder setErrorCode(String errorCode) {
            msg.errorCode = errorCode;
            return this;
        }

        public Builder setMoreInfo(String moreInfo) {
            msg.moreInfo = moreInfo;
            return this;
        }

        public Message build() {
            return msg;
        }
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
}
