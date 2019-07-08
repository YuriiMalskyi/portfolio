package com.malskyi.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.malskyi.project.entity.Category;
import com.malskyi.project.entity.Producer;
import com.malskyi.project.entity.UserEntity;
import com.malskyi.project.entity.enums.Roles;
import com.malskyi.project.repository.CategoryRepository;
import com.malskyi.project.repository.ProducerRepository;
import com.malskyi.project.repository.UserRepository;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableJpaRepositories(basePackages = "com.malskyi.project.repository")
public class ProjectApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProducerRepository producerRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		if(userRepository.count() == 0) {
			UserEntity user = new UserEntity();
			user.setId(0);//*1L);
			user.setUsername("admin");
			user.setFirstName("Admin");
			user.setLastName("Admin");
			user.setRole(Roles.ROLE_GLOBAL_ADMIN);
			user.setPassword(passwordEncoder.encode("admin"));
			
			userRepository.save(user);
		}
		
		if(categoryRepository.count() == 0) {
			String type[] = {"CPU", "GPU", "RAM", "Memory", "Motherboard", "Power supply"};
			for(int i = 0; i < 6; i++) {
				Category category = new Category();
				category.setId(i+1);//*1L);
				category.setName(type[i]);
				categoryRepository.save(category);
			}
		}
		
		if(producerRepository.count() == 0) {
			String producerList[] = {"Intel", "AMD", "Asus", "NVIDIA", "MSI", "Kingston"};
			for(int i = 0; i < 6; i++) {
				Producer producer = new Producer();
				producer.setId(i+1);//*1L);
				producer.setName(producerList[i]);
				producerRepository.save(producer);
			}
		}
	}
}

