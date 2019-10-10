# JavaProject

This is an academic project for NUS-ISS.

The system will accommodate three types of employee roles, namely: Administrators, Managers and Employees.

Main features:
- Employees are able to apply/cancel/update their leave.
- Managers are responsible for leave approval/rejection process. Managers can also print consolidated leave reports.
- Administrators are responsible for creating, managing users and respective roles. They are also responsible for managing the approval hierarchy.

There are three categories of leave that an employee is entitled to claim. They are annual leave, medical leave, and compensation leave. An employee has to take full day leave for all entitlement except compensation leave. For compensation leave the
granularity is half a day. Every four hours of overtime work makes an employee eligible
for half-a day compensation.

Optional features:
- Compensation Leave Management
Employees are allowed to claim compensation for overtime work, in a
granularity of half a day. This claim is subject to manager’s approval. Each
employee has an associated ledger to keep track of compensation leave and
respective claim day. When the employee claims to use his compensation leave,
it is again approved/rejected by the managers.

- Reporting
Managers must be able to produce various reporting views such as
  - Employee on annual/compensation/medical/all leave during a selected
particular period
  - Compensation claims for all/particular employee
  - Managers must be able to export the above reports to comma-delimited
(CSV) file format.

- Movement Register
This is a menu available for all users and upon clicking the hyperlink, the system
displays details of all employees on leave during the current month and the
category of leave. Users can also navigate to the previous and next month using
a dropdown choice list.

- Pagination
If many leave results are returned (say more than 30 records), the system should
present them over several pages. Each page will contain navigation facilities so
that other pages of search results can be shown (similar to search engine
results). The number of results per page should ideally be selectable by the user
(e.g. 10, 20, 25).

- Email interaction
When the employee applies for leave, system should send a notification e-mail
to his/her manager. Also the employee is informed of results on both accept and
reject cases. The email message should contain a direct link to the login page
where the user can view the comments.

My team has design, develop, test and release a web based application, using a
standard RDBMS such as MySQL. we used a model-view-controller architecture
using any web technology discussed in the lectures such as Spring MVC Framework and
Spring WebFlux. A reasonable business layer is expected to validate leave type, claim
dates, eligibility and approval processes. The persistent data layer can be implemented
either using Simple Data layers using JPA ORM framework.
