package com.magorasystems.mafmodules.network.config;


/**
 * @author Valentin S. Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public class SimpleServerEndpoint extends BaseServerEndpoint {


    private SimpleServerEndpoint(Builder builder) {
        super(builder.host, builder.path, builder.version);
    }

    @Override
    public String toString() {
        return "SimpleServerEndpoint{" +
                "url=" + getUrl() +
                "}";
    }

    public static final class Builder {
        private String host;
        private String path;
        private String version;

        public Builder() {
        }

        public Builder host(String val) {
            this.host = val;
            return this;
        }

        public Builder path(String val) {
            this.path = val;
            return this;
        }

        public Builder version(String val) {
            this.version = val;
            return this;
        }

        public ServerEndpoint build() {
            return new SimpleServerEndpoint(this);
        }
    }

}
