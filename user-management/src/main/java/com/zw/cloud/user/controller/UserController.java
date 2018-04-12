package com.zw.cloud.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zw.cloud.user.domain.User;
import com.zw.cloud.user.repository.UserRepository;

/**
 * 这边的@RefreshScope注解不能少，否则即使调用/refresh，配置也不会刷新
 * @author eacdy
 */
@RestController
@RefreshScope
public class UserController {
  @Value("${profile}")
  private String profile;
  
  @Autowired
  private DiscoveryClient discoveryClient;
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/hello")
  public String hello() {
    return this.profile;
  }
  
  /**
   * 注：@GetMapping("/{id}")是spring 4.3的新注解等价于：
   * @RequestMapping(value = "/id", method = RequestMethod.GET)
   * 类似的注解还有@PostMapping等等
   * @param id
   * @return user信息
   */
  @GetMapping("/{id}")
  public User findById(@PathVariable Long id) {
    User findOne = this.userRepository.findOne(id);
    return findOne;
  }

  /**
   * 本地服务实例的信息
   * @return
   */
  @GetMapping("/instance-info")
  public ServiceInstance showInfo() {
    ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
    return localServiceInstance;
  }
}
