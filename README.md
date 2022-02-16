# JavaPoised

A work in-progress project management system for the commandline.

## Context

A project management system to juggle information pertaining to building construction projects.

Project attributes tracked in this program include:

1. Project number
2. Project name
3. Building type (House, apartment block or store, etc.)
4. Project physical address
5. Project ERF number
6. Project fee
7. Fee paid-to-date
8. Project deadline
9. Architect contact: name, telephone number, email address, physical address
10. Contractor contact: name, telephone number, email address, physical address
11. Customer contact: name, telephone number, email address, physical address

## Application Features

1. Capture information about new projects
2. Update information about existing projects
3. Finalise existing projects:
  - Invoice is generated for the client. Details customer’s contact details and the amount the customer must still pay
  - If customer has already paid the full fee, invoice will not be generated
4. List all projects to be completed
5. List projects past their due date
6. Find and select projects by project number or project name
