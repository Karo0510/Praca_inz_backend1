package com.example.bhp.createViews;

import com.example.bhp.dao.EmployeeInfo;
import com.example.bhp.dao.JobInfo;
import com.example.bhp.entity.Employees;
import com.example.bhp.entity.JobPosition;
import com.example.bhp.entity.RegistryKey;
import com.example.bhp.entity.RegistryOfAccidents;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class AccidentDetails extends AccidentBasics {
    public List<Long> IdEmployees = new ArrayList<>();

    public AccidentDetails() {

    }

    public AccidentDetails setData(RegistryOfAccidents info) {
        super.setData(info);

        for (Employees emp : info.getEmployees()) {
            this.IdEmployees.add(emp.getId());
        }

        return this;
    }

    public RegistryOfAccidents createAccident()
    {
        //XXX: jest null -> wywala NullPointerException

        Integer number = -1;
        Long year = -1L;

        String[] entry = this.getProtocole().split("-");
        year = Long.parseLong(entry[1]);
        number = Integer.parseInt(entry[0]);



        Long accidentId = Long.parseLong(number.toString() + year.toString());

        RegistryOfAccidents accident = RegistryOfAccidents.builder()
                .protocole(this.getProtocole())
                .type(this.getAccident_priority())
                .number(Math.toIntExact(number))
                .key(new RegistryKey(accidentId, this.getDepartment()))
                .date(this.getDate())
                .isAccident(this.isAccident())
                .build();

        List<Employees> list = new ArrayList<>();

        for (int j = 0; j<this.IdEmployees.size(); j++)
        {
            Employees emp = EmployeeInfo.findEmployeeById((long) this.IdEmployees.get(j));
            emp.getRegister_of_accidents().add(accident);
            list.add(emp);
        }

        accident.setEmployees(list);

        return accident;
    }

}
