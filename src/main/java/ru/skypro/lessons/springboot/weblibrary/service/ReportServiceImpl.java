package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.Employee;
//import ru.skypro.lessons.springboot.weblibrary.Report;
//import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;
//import ru.skypro.lessons.springboot.weblibrary.repository.ReportRepository;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class ReportServiceImpl implements ReportService{
//    private final ReportRepository reportRepository;
//    private final EmployeeRepository employeeRepository;
//
//    public ReportServiceImpl(ReportRepository reportRepository, EmployeeRepository employeeRepository) {
//        this.reportRepository = reportRepository;
//        this.employeeRepository = employeeRepository;
//    }
//
//    @Override
//    public int createReport() {
//        List<Employee> a =employeeRepository.findAllEmployees();
//        Report b = new Report();
//        int id=0;
//        b.setPos_name(a.get(0).getPosition());
//        a.stream()
//                .collect(Collectors.toList());
//        int count=0;
//        for (int i = 0; i < a.size(); i++) {
//            count+=a.get(i).getSalary();
//        }
//        count=count/a.size();
//        a.stream()
//                .sorted(Comparator.comparing(Employee::getSalary))
//                .collect(Collectors.toList());
//        b.setAvg_salary(count);
//        b.setCount_employees(a.size());
//        b.setMax_salary(a.get(a.size()-1).getSalary());
//        b.setMin_salary(a.get(0).getSalary());
//        b.setId_report(id);
//        id++;
//        reportRepository.save(b);
//        return id;
//    }
//
//    @Override
//    public Report getReportById(int id) {
//        return reportRepository.findReportById(id);
//    }
//}
