package org.ngvhuy.SpringDataJpaWithCache;

import org.ngvhuy.SpringDataJpaWithCache.entity.Dataset;
import org.ngvhuy.SpringDataJpaWithCache.entity.DatasetValue;
import org.ngvhuy.SpringDataJpaWithCache.entity.User;
import org.ngvhuy.SpringDataJpaWithCache.repository.DatasetRepository;
import org.ngvhuy.SpringDataJpaWithCache.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping
public class SpringDataJpaWithCacheApplication {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DatasetRepository datasetRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaWithCacheApplication.class, args);
	}

	@Transactional(readOnly = true)
	@GetMapping("/get-all")
	public String getAll() {
		Long id = 10L;
		List<User> users1 = userRepository.findAll();
		System.out.println("List user:");
		users1.forEach(System.out::println);

		List<User> users2 = userRepository.findAll();
		System.out.println("List user:");
		users2.forEach(System.out::println);

		User userLoad1 = userRepository.findById(id).get();

		System.out.println(userLoad1.getFullname());

		User userLoad2 = userRepository.findById(id).get();
		System.out.println(userLoad2.getFullname());

		User userLoad3 = userRepository.findByUsername("username");
		System.out.println("after find by username");

		return "Let see console !!";
	}

	@GetMapping("/test-save")
	public String testSave() throws InterruptedException {
		Dataset dataset = Dataset.builder()
				.name("name1").fields("LAC_CELL").rangeInput(false)
				.createdAt(Instant.now()).createdBy("Admin")
				.updatedAt(Instant.now()).updatedBy("Admin").build();

		DatasetValue value = DatasetValue.builder()
				.dataset(dataset).value("111_222")
				.createdAt(Instant.now()).createdBy("Admin")
				.updatedAt(Instant.now()).updatedBy("Admin").build();

		List<DatasetValue> values = List.of(value);
		dataset.setDatasetValues(values);

		System.out.println("Id của dataset trước khi save: " + dataset.getId());
		System.out.println("Id của value trước khi save: " + value.getId());

//		datasetRepository.saveAndFlush(dataset);
		datasetRepository.save(dataset);

		Thread.sleep(10_000);

		System.out.println("Id của dataset sau khi save: " + dataset.getId());
		System.out.println("Id của value sau khi save: " + value.getId());

		return "Let see console !!";
	}

	@GetMapping("/test-get-one")
	public String testGetOne() {
		Long id = 10L;
		datasetRepository.findById(id);
		return "Let see console !!";
	}

}
