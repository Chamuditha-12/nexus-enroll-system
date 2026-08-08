# NexusEnroll - University Course Enrolment System

A modernised university course enrolment system built as a proof-of-concept 
for the Software Architecture (SCS 2303) module, University of Colombo School 
of Computing (UCSC).

## Overview
NexusEnroll replaces a legacy monolithic enrolment system with a scalable, 
maintainable 3-Tier architecture, demonstrating core software design 
principles (SOLID) and object-oriented design patterns.

## Modules
- Student Module - course browsing, enrolment/drop, schedule, academic progress
- Faculty Module - class rosters, grade submission, course change requests
- Administrator Module - course/program management, reporting & analytics

## Architecture
3-Tier Architecture: Presentation Tier -> Business Logic Tier -> Data Tier

## Design Patterns Used
- Factory Method - user creation (Student/Faculty/Admin)
- Strategy - enrolment validation rules
- Observer - notification system (waitlist, advisor alerts)
- State - grade lifecycle (Pending -> Submitted)
- Facade - simplified enrolment service interface

## Tech Stack
- Language: Java / C++ (confirm which one your group finalizes)

## Assignment
Software Architecture (SCS 2303) - Assignment 3
Deadline: 20 Aug