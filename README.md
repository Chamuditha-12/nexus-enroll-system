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
- Language: Java

## Assignment
Software Architecture (SCS 2303) - Assignment 3
Deadline: 20 August 2026

## MIT License

Copyright (c) 2026 NexusEnroll Contributors

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
  
