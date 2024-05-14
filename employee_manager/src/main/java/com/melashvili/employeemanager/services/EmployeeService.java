package com.melashvili.employeemanager.services;

import com.melashvili.employeemanager.model.Admin;
import com.melashvili.employeemanager.model.Employee;
import com.melashvili.employeemanager.model.EmployeeTier;
import com.melashvili.employeemanager.model.Sector;
import com.melashvili.employeemanager.repository.AdminRepository;
import com.melashvili.employeemanager.repository.EmployeeRepository;
import com.melashvili.employeemanager.repository.EmployeeTierRepository;
import com.melashvili.employeemanager.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final SectorRepository sectorRepository;

    private final EmployeeTierRepository employeeTierRepository;

    private final AdminRepository adminRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           SectorRepository sectorRepository,
                           EmployeeTierRepository employeeTierRepository,
                           AdminRepository adminRepository) {
        this.employeeRepository = employeeRepository;
        this.sectorRepository = sectorRepository;
        this.employeeTierRepository = employeeTierRepository;
        this.adminRepository = adminRepository;
    }

    public List<Employee> getAllEmployees() {
        Iterable<Employee> temp = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : temp) {
            employees.add(employee);
            System.out.println(employee.toString());
        }
        return employees;
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
        System.out.println(employee.toString());
    }

//    public void addEmployee(Employee employee, Long sectorId,
//                            Long adminId, Long tierId) {
//        System.out.println(sectorId);
//        System.out.println(adminId);
//        System.out.println(tierId);
//
//        Sector sector = null;
//        Admin admin = null;
//        EmployeeTier employeeTier = null;
//
//        if (sectorId != null) {
//            sector = sectorRepository.findById(sectorId).orElse(null);
//        }
//        if (adminId != null) {
//            admin = adminRepository.findById(adminId).orElse(null);
//        }
//        if (tierId != null) {
//            employeeTier = employeeTierRepository.findById(tierId).orElse(null);
//        }
//
//        employee.setSector(sector);
//        employee.setAdmin(admin);
//        employee.setTier(employeeTier);
//
//        System.out.println(sector.toString());
//        System.out.println(admin.toString());
//        System.out.println(employeeTier.toString());
//        System.out.println(employee.toString());
//
//        employeeRepository.save(employee);
//    }

    public void updateEmployeeById(Long id, Employee updatedEmployee) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isPresent()) {
            Employee employeeToUpdate = employeeOptional.get();

            employeeToUpdate.setEmployeeFirstName(updatedEmployee.getEmployeeFirstName());
            employeeToUpdate.setEmployeeLastName(updatedEmployee.getEmployeeLastName());
            employeeToUpdate.setAdmin(updatedEmployee.getAdmin());
            employeeToUpdate.setSector(updatedEmployee.getSector());
            employeeToUpdate.setTier(updatedEmployee.getTier());

            employeeRepository.save(employeeToUpdate);
        } else {
            System.out.println("Employee not found");
        }
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
