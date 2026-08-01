## 1. Project එක මොකක්ද

**NexusEnroll** = University enrollment system එකක් (LegacyEnroll කියන පරණ system එකක් replace කරන්න). අපි **design + POC code එකක්** කරන්නේ, full production system එකක් නෙවෙයි.

**3 core modules:**
- **Student** - course browse, enroll/drop, schedule, progress tracking
- **Faculty** - class roster, grade submission, course change requests
- **Administrator** - course/user management, reports

---

## 2. Architecture Pattern - **3-Tier Architecture**

Assignment එකේ options 3ක් තිබුණා (Microservices, SOA, 3-Tier). අපි **3-Tier** තෝරාගත්තා.

### 3-Tier කියන්නේ මොකක්ද - System එක Layer 3කට බෙදනවා:

| Layer | Responsibility | අපේ Code එකේ Folder |
|---|---|---|
| **Presentation Tier** | User (Student/Faculty/Admin) interact වෙන CLI menus | `cli/` |
| **Business Logic Tier** | Core rules, validations, patterns | `services/` + `patterns/` |
| **Data Tier** | Data storage (in-memory objects) | `models/` |

### ඇයි 3-Tier තෝරාගත්තේ?
- Team 5-6ට simple, manage කරන්න ලේසි
- Layer එකින් එකට වෙනම වෙනස් කරන්න පුළුවන් (UI වෙනස් කළත් business logic එකට බලපෑමක් නෑ)
- Future integration (financial aid system වගේ) ලේසියෙන් add කරන්න පුළුවන්
- Microservices/SOA එකට වඩා **simple + justify කරන්න ලේසි**

---

## 3. Design Patterns - **5ක් Use කරනවා** (Assignment එකට minimum 3ක් ඕන)

| # | Pattern | Type | භාවිතා කරන්නේ මොකටද |
|---|---|---|---|
| 1 | **Factory Method** | Creational | Student/Faculty/Admin objects **හදන්න** (`new Student()` වෙනුවට `UserFactory.createUser()`) |
| 2 | **Strategy** | Behavioural | Enrollment validation rules - Prerequisite check, Capacity check, Time-conflict check (**වෙනම class එකක් එකින් එකට**) |
| 3 | **Observer** | Behavioural | Notification system - student drop කරාම waitlist එකේ අනිත් student ට / advisor ට automatically notify කරනවා |
| 4 | **State** | Behavioural | Grade lifecycle - "Pending" → "Submitted" (grade එකේ status track කරන්න) |
| 5 | **Facade** | Structural | `EnrollmentService` - complex validation logic **එක simple method call එකකට** hide කරනවා |

### Direct simple explanation එකක් (member කෙනෙක් අහන්න පුළුවන් "ඇයි මේ pattern එක?" කියලා):
- **Factory** = "object එකක් හදන්න logic එකක් centralize කරනවා" (code duplicate වෙන එක නවත්තනවා)
- **Strategy** = "rule එකින් එකක් වෙනම class එකක් - අලුත් rule එකක් add කරන්න ඕන නම් existing code වෙනස් කරන්න ඕන නෑ"
- **Observer** = "event එකක් වුණාම, ඒකට interested සියලු දෙනාටම automatically notify කරනවා" (student/advisor/admin)
- **State** = "object එකක status එකට අනුව behavior එක වෙනස් වෙනවා" (Pending grade එකක් edit කරන එකයි Submitted grade එකක් edit කරන එකයි වෙනස්)
- **Facade** = "complex process එකක් simple interface එකකින් hide කරනවා" (client code එකට validation logic ගැන දැනගන්න ඕන නෑ, එකම method call එකක් කරනවා)

---

## 4. Programming Language - **Java**

**ඇයි Java?**
- Assignment options: C++, C#, Java, Python - Java **best fit**
- OOP concepts (interfaces, abstract classes) clean විදියට express කරන්න පුළුවන්
- Design patterns ලියන්න industry-standard language එක
- අපි C++ දන්නවා, Java syntax එකට **very close** (braces, semicolons, classes - සියල්ල සමානයි)

---

## 5. File Structure (Project Tree)

```
NexusEnroll/
├── src/
│   └── nexusenroll/
│       ├── Main.java
│       ├── models/          (Data Tier - Student, Faculty, Course, etc.)
│       ├── patterns/         (Design Patterns - factory/, strategy/, observer/, state/)
│       ├── services/         (Business Logic - EnrollmentService, FacultyService, AdminService)
│       └── cli/               (Presentation Tier - StudentMenu, FacultyMenu, AdminMenu)
└── test/
    └── TestScenarios.java   (Testing/demo scenarios)
```

**Simple කරලා කියනවනම්:**
- `models/` = Data classes (Student කෙනෙක්ට තියෙන fields මොනවද)
- `patterns/` = Pattern එකින් එකට වෙනම folder (Factory, Strategy, Observer, State)
- `services/` = Actual business logic (validation, grading, reporting)
- `cli/` = User menus (console එකේ "1. View Courses" වගේ options)

---

## 6. Core Validation Rules (Enrollment වලට අනිවාර්ය)

Student කෙනෙක් course එකකට enroll වෙද්දී **check 3ක්** pass වෙන්න ඕන:
1. **Prerequisite check** - required courses complete කරලාද
2. **Capacity check** - course එකේ seats available ද
3. **Time-conflict check** - already enrolled course එකකට schedule එක clash වෙනවද

මේ check 3ම **Strategy pattern** එකෙන් implement කරනවා (checker එකින් එකක් වෙනම class එකක්).



