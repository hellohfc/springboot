package com.example.demo;


import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

import static org.bouncycastle.crypto.tls.ConnectionEnd.client;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private DiscoveryClient discoveryClient;

	@Value("${server.port}")
	String port;
	@RequestMapping("/hi")
	public String home(@RequestParam String name) throws Exception{
		/*ServiceInstance instance = discoveryClient.getLocalServiceInstance();

		//让处理线程等待几秒钟
		int sleepTime = new Random().nextInt(3000);
		System.out.println("+++++++++++sleepTime is "+sleepTime);


		Thread.sleep(sleepTime);*/

		return "hi"+name+",i am from port:"+port;
	}

	/**
	 * ribbon消费者调用的接口
	 * @param name
	 * @return
	 */
	@RequestMapping("/hiribbon")
	public String homeRibbon(@RequestParam String name,HttpServletRequest request,@RequestParam String password){
		//request.getRequestDispatcher
		return "hi"+name+",i am ribbon,"+"my port is:"+port+" and password is "+password;

	}
	@RequestMapping("/home")
	public String home(){
		return "this is home page";
	}

	@RequestMapping(value="hellouser",method = RequestMethod.GET)
	public User hello(@RequestHeader String name,@RequestHeader Integer age){
		return new User(name,age);
	}

	@RequestMapping(value="hellouser1",method = RequestMethod.POST)
	public String hello(@RequestBody User user){
		return "hello "+user.getUsername()+","+user.getAge();
	}
}
