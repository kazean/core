package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient
//        implements InitializingBean, DisposableBean
{
    private String url;

    public NetworkClient() {
        System.out.println("생성자호출, url - " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect(){
        System.out.println("connect:" + url);
    }

    public void call(String message){
        System.out.println("call: " + message + " url: "+url);
    }

    public void disconnect(){
        System.out.println("close:" + url);
    }
    /*
    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메세지");
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }
    */

    @PostConstruct
    public void init() throws Exception {
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() throws Exception {
        disconnect();
    }
}
