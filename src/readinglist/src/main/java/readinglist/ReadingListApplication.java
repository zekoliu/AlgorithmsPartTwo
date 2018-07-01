package readinglist.src.main.java.readinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication		//开启组件扫描和自动配置
public class ReadingListApplication extends WebMvcConfigurerAdapter{	//配置和启动引导类

	public static void main(String[] args) {
		SpringApplication.run(ReadingListApplication.class, args);	//负责启动引导应用程序
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/readinglist");
	}
}
