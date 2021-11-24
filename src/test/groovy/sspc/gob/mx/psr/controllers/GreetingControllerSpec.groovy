package sspc.gob.mx.psr.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import sspc.gob.mx.psr.validator.GreetingValidator

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetingControllerSpec extends Specification{

	@Value('${local.server.port}')
	int port
	RestTemplate rest = new RestTemplate()

	def "should respond a standard greeting"() {
		when:
		def resp = rest.getForEntity("http://localhost:${ port }/greetings", Map).body
		then:
		resp.message == 'hello from spring boot'
	}

	def "should respond a personal greeting"() {
		given:
		String name = 'Darth Vader'
		when:
		def resp = rest.getForEntity("http://localhost:${ port }/greetings/$name", Map).body
		then:
		resp.message == "hello $name"
	}

	def "should respond a query id"() {
		given:
		int id = 666
		when:
		def resp = rest.getForEntity("http://localhost:${ port }/greetings/query?id=$id", Map).body
		then:
		resp.message == "ID: $id"
	}

    def "Should post"(){
		given:'a body request'
		HttpHeaders headers = new HttpHeaders()
		headers.setContentType(MediaType.APPLICATION_JSON)
		GreetingValidator cmd = new GreetingValidator()
		cmd.with {
			name = 'Alejandro'
			alias = 'pinky'
		}

		when:
		def resp = rest.postForObject("http://localhost:${ port }/greetings", new HttpEntity(cmd, headers), Map)

		then:
		resp.with {
			assert it.name == cmd.name
			assert it.alias == cmd.alias
		}
	}


}
