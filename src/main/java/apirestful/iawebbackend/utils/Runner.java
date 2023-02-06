package apirestful.iawebbackend.utils;

import apirestful.iawebbackend.model.Event;
import apirestful.iawebbackend.model.Rol;
import apirestful.iawebbackend.model.Turn;
import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.repository.RolRepository;
import apirestful.iawebbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class Runner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;

    public Runner(UserRepository userRepository, RolRepository rolRepository){
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        if(this.rolRepository.count() == 0 && this.userRepository.count()==0){
            Rol adminRol = new Rol(1L,"ADMIN","ROL DE ADMINISTARDOR",timestamp);
            Rol socioRol = new Rol(2L,"SOCIO","ROL DE SOCIO",timestamp);
            Rol evaluadorRol = new Rol(3L,"EVALUADOR","ROL DE EVALUADOR",timestamp);
            Set <Rol> roles = new HashSet<>();
            roles.add(adminRol);
            roles.add(socioRol);
            roles.add(evaluadorRol);
            Set<Event> event = new HashSet<Event>();
            Set<Turn> turn = new HashSet<Turn>();
            this.rolRepository.saveAll(roles);
            User admin = new User("001","admin","atmira","cordoba","admin@atmira.com","URL Image","admin",new BCryptPasswordEncoder().encode("admin"),"Puesto","Oficina","España",timestamp, roles,event,turn);
            User socio = new User("002","socio","atmira","cordoba","socio@atmira.com","URL Image","socio",new BCryptPasswordEncoder().encode("socio"),"Puesto","Oficina","España",timestamp, roles,event,turn);
            User evaluador = new User("003","evaluador","atmira","cordoba","evaluador@atmira.com","URL Image","evaluador",new BCryptPasswordEncoder().encode("evaluador"),"Puesto","Oficina","España",timestamp, roles,event,turn);
            ArrayList <User> usuarios = new ArrayList();
            admin.setRols(Collections.singleton(adminRol));
            socio.setRols(Collections.singleton(socioRol));
            evaluador.setRols(Collections.singleton(socioRol));
            usuarios.add(admin);
            usuarios.add(socio);
            usuarios.add(evaluador);
            this.userRepository.saveAll(usuarios);
        }
    }
}
