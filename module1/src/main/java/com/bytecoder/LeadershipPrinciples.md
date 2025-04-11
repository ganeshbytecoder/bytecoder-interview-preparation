Clear business context
Your ownership and thought process

Situation → Task → Action → Result with Impact/metrics -> What you learned



## 💡 **Frugality**
*"Accomplish more with less. Constraints breed resourcefulness."*

### ✅ Example: Migrating to PySpark to reduce infra cost and processing time

- **Situation**: At Kaleidofin, our Spring Batch-based data pipelines for risk infra were slow and resource-intensive, taking over **17 hours** to process partner data daily.
- **Task**: We needed to scale for new partners while minimizing infrastructure costs, but without compromising performance.
- **Action**: I led the initiative to migrate to **Apache PySpark**, rewriting batch jobs to be more distributed and efficient. We reused the same AWS EMR cluster for multiple jobs instead of creating new clusters, and avoided heavy compute scaling.
- **Result**: Processing time reduced from **17 hours to 1.5 hours**, and we cut **EC2/compute costs by 40%**. This enabled onboarding of more partners without increasing our infrastructure footprint.

---

## 💡 **Strive to Be Earth’s Best Employer**
*"Workplace safety, employee development, well-being, and inclusion."*

### ✅ Example: Mentorship, documentation, and creating a culture of growth

- **Situation**: At Kaleidofin, as the team grew rapidly, new hires struggled to ramp up on the complex risk and payment infrastructure.
- **Task**: I wanted to ensure smooth onboarding and promote a culture of continuous learning, collaboration, and psychological safety.
- **Action**: I initiated **weekly knowledge-sharing sessions**, created **detailed onboarding documents**, and established **coding best practices** across teams. I encouraged juniors to speak up during code reviews and designed a mentorship structure for interns and new joiners.
- **Result**: Ramp-up time dropped by 50%, team engagement increased, and we promoted 2 junior engineers into more independent roles. We built a stronger, inclusive culture even during tight delivery timelines.

---

## 💡 **Success and Scale Bring Broad Responsibility**
*"Act on behalf of future generations. Think beyond immediate results."*

### ✅ Example: Building scalable, ethical infrastructure with compliance and customer safety in mind

- **Situation**: At Kaleidofin, our services began handling data for over **10 million customers**, including sensitive financial and repayment data.
- **Task**: As our reach expanded, I had to ensure that our systems were **scalable**, **secure**, and **compliant**, especially with regulatory and partner-specific requirements.
- **Action**: I designed multi-tenant architectures for risk and repayment services, built retry mechanisms for failed deductions, and implemented **audit trails** and **circuit breakers** to ensure resilience. I pushed for **automated validations** to avoid human errors in partner onboarding.
- **Result**: Our platform scaled to support **6M+ customers without service disruption**, and we maintained **100% uptime** for key APIs. It also passed partner and internal audits with no critical observations, strengthening trust across the ecosystem.

---


## 3. **Customer Obsession**
- Smarttrak - payments issue in system taking to much time to show history of payments
- Kaleidofin: File sharing dashboard using reactjs 
- Kaleidofin: made faster onboarding customer using configs file based 
- Resolving urgent carrier onboarding issues at Fourkites.


## 2. Ownership
### 1) **Tell me about a time when you took on something significant outside your area of responsibility. Why was it important? What was the outcome?**
- Kaleidofin : Knowledge sharing sessions to set standards 
- Kaleidofin: File sharing dashboard using reactjs
- Kaleidofin: made faster onboarding customer using configs file based
- Kaleidofin : collaboration with devops for best infra and cicd
- Smarttrak - payments issue in system taking to much time to show history of payments



### 2) **Describe a time when you didn't think you were going to meet a commitment you promised. How did you identify the risk and communicate it? What would you do differently?**
- Kaleidofin : risk-infra for feature creation. data discrepancy issue 
- Kaleidofin : repayment service split to have unified payment system 
- Smarttrak : kafka integration took more time than expected 

### 3) **Give me an example of an initiative you undertook because you saw it would benefit the whole company/customers but wasn’t clearly anyone’s responsibility.**
- Smarttrak- Kafka-based event-driven architecture for real-time solar plant data ingestion. Due to some multi-tenant complexities
- Smarttrak : data processing and feature creation pipeline optimisation 
- Kaleidofin: file sharing dashboard 


## 3. Dive Deep

### 1) **Tell me about a time you had to dig into the details to figure out a complex problem. Who did you talk to or what did you look at? How did you solve it?**
- kaleidofin:  monolith applications to microservices
- Rupeek:  understanding of 40+ microservices to make migration smooth
- Kaleidofin - payment service connection time-out issue and pool was fulled
- Smarttrak - payments issue in system taking to much time to show history of payments



### 2) **Tell me about a situation that required you to dig deep to get to the root cause. How did you know you were focusing on the right things?**
- Kaleidofin: sometime kiscore pipeline were getting stuck in middle of processing due memory issue. memory was not clearing. i checked logs and grafana for disk and memory spaces 
- Kaleidofin: data sharing API stats were high for failures. deep dig and found connection time out after enabling db level logs  
- Kaleidofin: In the FinTech infrastructure migration project, some microservices randomly failed health checks in Kubernetes. The restarts were costly and impacted service uptime SLAs.
  - found memory leaks and added proper logs to analyse the rca 
- Smarttrack: sometime payment service taking too much time to get the result and were not able to connect with  db and creating latency due timeout set in db and service
- 
  

### 3) **Tell me about a problem you had to solve that required in-depth thought and analysis.**
- Kaleidofin: After joining Kaleidofin, we decided to transition from a monolithic architecture to microservices. needed to know everything and in detailed
- Kaleidofin : risk-infra for feature creation. i needed to know all about machine learning and features 
- Smarttrak:  pipelines for llm and ml features and RAG pipeline


## 4. Learn and Be Curious

### 1) **What is the coolest thing you have learned on your own that has helped you do your job better?**
- Kaleidofin: learning spark helped me to understand big data processing and made whole processing easy and after 
- Kaleidofin:  learning react js helped to know fron-end and how the things should be integrated and their point of view while discussion 
- Rupeek: devops services like vault, api gateway, aws services, k8s and helm etc

### 2) **Tell me about a time you realized you needed deeper subject-matter expertise to do your job well.**
- Smarttrak:  pipelines for llm and ml features and RAG pipeline
- Kaleidofin: After joining Kaleidofin, we decided to transition from a monolithic architecture to microservices. needed to know everything and in detailed
- Rupeek:  need all the details about all microservice before setting standerds and understanding of that for new infra


### 3) **Tell me about a time you took on work outside your comfort zone and found it rewarding.**
- KT sessions at kaleidofin to set the bar for code standerds 
- Kaleidofin: moving from spring batch to spark. i learn spark and did a success poc
- Kaleidofin:  learn react js  and created file sharing dashboard for partner

## 5. Earn Trust

### 1) **Tell me about a time when you had to communicate a change in direction that you anticipated people would have concerns about.**
- Kaleidofin: moving from spring batch to spark
- Kaleidofin: multi-tent architecture for all partners 
- Kaleidofin: After joining Kaleidofin, we decided to transition from a monolithic architecture to microservices. Some teammates were accustomed to the monolith’s simplicity and were worried about the complexity of distributed systems.

### 2) **Give me an example of a tough or critical piece of feedback you received. What was it and what did you do about it?**
- Smarttrak:  I gave a presentation on optimizing batch processing from 27 hours to under 50 minutes. While the technical details were well received, leadership feedback was that I wasn’t quantifying the business impact clearly enough.
- Kaleidofin: got sign off from some of devs but got on halt since it was not shared at wider audience. and they were not happy to halt the pipeline for that 
- 

### 3) **Describe a time when you needed to influence a peer who had a differing opinion about a shared goal.**
- Kaleidofin: suggested Pyspark over spring batch. they were sticking with that since lack tech and already infra  
- Smarttrak: suggested to create Auth service and remove boilerplate code in each microservice for security
- Smarttrak: use of timeseries db timescale db instead of postgres only for real-time dashboard at smarttrak and use of aggregation pipeline 






### 4) **Tell me about a piece of direct feedback you recently gave to a colleague. How did they respond? How do you like to receive feedback?**
- Smarttrak: some of the juniors joined in the team after reviewing their MR i found they are following different set of standards code convention i connected and understand their point of and shared ours and ask to go through some docs. eventually they agreed with ours and created check list follow before any MR
- Smarttrak: architectural change for API call to one for devs which were make a feature for a service. shared db with two microservice, show they created new same entity but i suggsted to make api call and use cache so new changes will not break their service
- 


## 6. Invent and Simplify
### 1) **Give me an example of a complex problem you solved with a simple solution.**
- Kaleidofin: multiple repayment logics for each partners or BCs -> unified “repayment funnel” that captured all payments
- Kaleidofin: File sharing dashboard for file sharing for partner/lenders -> partner were happy to see that since they not have to go through sftp or mail 
- Kaleidofin: used yml format configs file instead of changes at DB level configs based onboarding of a partners 
- Rupeek: Centralised dashboard for rupeek to devops and devs to monitor services and alerts

### 2) **Describe the most innovative thing you’ve done and why you thought it was innovative.**
- Kaleidofin: Monolith to microservice not easy but set standards to be followed and gave KT sessions which made it easy after making monolith application stateless and everyone followed it 
- Kaleidofin: Risk infra pipelines  for kiview and kiscore features -> 75% time amd money reduced and brought more business like federal bank and other banks
- Rupeek: common pages service registry after standard doc for all 40+ microservices and discussing with each of the team and understand - this made migration possible 


### 3) **Tell me about a time when you were able to make something simpler for customers.**
- Kaleidofin: dashboard for file sharing for partner/lenders -> partner were happy to see that since they not have to go through sftp or mail or sharepoint
- Kaleidofin: configs based onboarding of a partners not manual db changes -> used yml format
- Rupeek: Custom dashboard for rupeek to devops and devs to monitor services deployments and API docs and all other applications links
- 

## 7. Insist on the Highest Standards
### 1) **Tell me about a time you wouldn’t compromise on achieving a great outcome when others felt something was good enough.**
- Kaleidofin: spring batch to pyspark to support distributed processing for big data
- Kaleidofin - standardised doc to be followed by each team to we will not have tech debts in future like test coverage 80-98%
- Kaleidofin: multi-tenant standards - people suggested to replicate microservice for each partner(single tenancy) but i went for multi-tenet architecture 

### 2) **Tell me about a time when you were unsatisfied with the status quo. What did you do to change it? What was the impact?**
- Kaleidofin: One of the team did not follow standards set by us and pushing changes ven less test coverage to make faster but this created prod issue in one of microservice which was call was failing and set jenkins checks not allowing any app less match rate and code standards
- Kaleidofin: DS team wanted features to be created ib pyspark pipeline but supprt was very less and even after results they took did not give the priority to that. which blocked us for further processing. we are concerns at wider audiance
- Smarttrak: live agregation on real-time data which were creating latency and the we went for aggregation batch processing



### 3) **Tell me about a time you worked to improve the quality of a product that was already getting good feedback.**
- Kaleidofin: after switching from spring batch to pyspark processing was taking 17hours to 1.5hours but still improved the quality of risk infra and reduced kiview processing with 20% more
- Kaleidofin: Knowledge sharing sessions and code standerdisation in all microservices 
- Kaleidofin: while developing kiscore pipeline. we had achieved performance from target 40% and but i insisted more improvement and we went for that and achieved more than 75% and 33H to 7H
- Smarttrak: payment updated were ok but but giving error sometime.


### 4) **Give me an example of a goal you’ve had where you wish you had done better. What was the goal and how could you have improved?**
- Rupeek:  I would have all the info of all microservices first and should have handle migration better with proper planning and doc. 
- Kaleidofin : would have been risk-infra better and faster we did feature creation 1 year back but were blocked since it was not match feature and did not have support
- Kaleidofin: got sign off for kiview partner sonata but letter it was paused due to issue. i would sent details to broader audiance and get the clararity so we would have done that job better 
- Kaleidofin:  same for kiscore


## 8. Have Backbone; Disagree and Commit

### 1) **Tell me about a time when you strongly disagreed with your manager or peer on something important. How did you handle it? Would you do anything differently?**
- kaleidofin wanted to push partner features faster but since i already see the data issue in past i disagree on timeline 
- partner oboarding timeline since
- continue with spring batch not pyspark 
- multiple payment services for each partner since we need multi-tenant architecture and using kafka for 100% availibity 
- smarttrak we were pushing all events in time series and i was suggesting spark batch processing for agregation 
- architectural changes for different devices and data validation before pushing , 

### 2) **Describe a time when you took an unpopular stance in a meeting with peers. What was it, why did you feel strongly about it, and what was the outcome?**
- Kaleidofin: Single-tenancy vs multi-tenancy. i strongly felt multi-tenancy is best at the level since less resources and fast developments and best to serve everyone
- Kaleidofin: making Monolith application to stateless and then Split monolith application to 3 microservices only not more than that since requires more resources
- Smarttrak : creating Auth-service instead of keeping security layers in each service. which is over-head to keep for all partners


## 9. Deliver Results - 
Leaders focus on the key inputs for their business and deliver them with the right quality and
in a timely fashion. Despite setbacks, they rise to the occasion and never settle.

### 1) **Give me an example of a time when you were able to deliver an important project under a tight deadline.**
  - Smarttrak:  was struggling with maintaining data with microservices using api  so integrated kafka and event drive architecture to have 100% uptime
  - Smarttrak:  pipeline optimisation for feature creation from 27H to 1H
  - kaleidofin: spring batch to PySpark pipeline since data was huge partners were coming and we did not have much time to explore options
  - kaleidofin: Kiscore features using PySpark so we can support partners who huge more bigger data size
  - independent repayment microservice

### 2) **Tell me about a time when you had significant, unanticipated obstacles to overcome in achieving a key goal.**
  - Rupeek : dockerisation for 40+ microservice we having big troubles because of stateful
  - kaleidofin: kal-server to miscroservices lot of dead code and outdated apis and creating microservices from that monolith application
  - kaleidofin : data issue in kiscore partners feature creation   
  - Smarttrak : optimisation of current pipeline for preprocessing and preprocessing 

### 3) **Tell me about a time when you not only met a goal but considerably exceeded expectations.**
 - Kaleidofin: making system scalable at kaleidofin with KT, docs, approvals, microservces and helping devops in their work
 - Kaleidofin: success of risk infra and serving 10M customers data preprocessing 
 - Kaleidofin : Repayment srevice is having 6M customers with any failure and multitenet architecture
 - Kaleidofin: kafka integration for repayment apis , retry mechanism, scheduler for auto-deductions(mandates), and circuit breakers

## 10. Are Right, A Lot : Leaders are right a lot. They have strong judgment and good instincts. They seek diverse perspectives and work to disconfirm their beliefs.

### 1) **Tell me about a time when you didn't have enough data to make the right decision. What did you do? Did it turn out correct?**
- choose between continuing with Spring Batch or migrating to PySpark.
- removal of outdated apis missing proper logging and actuator and micrometer  
- We have different repayment logics for each partner and wanted unified APIs and  one dedicated microservice 

### 2) **Tell me about a strategic decision you had to make without clear data or benchmarks.**
- While at Smarttrak, we debated adopting a new time-series database for solar plant telemetry. The current one wasn’t meeting performance needs, but we lacked direct benchmarks for our specific use case.
- schema changes in smartrak for new partner's devices since i did not have solid dataset to have different data points for that devices 
- We have different repayment logics for each partner and wanted unified APIs and  one dedicated microservice

### 3) **Tell me about a time when you made a bad decision.**
- got sign off by team members but missed broader audience verification later we had to re-validate againa share the output
- underestimating code refactoring for better to make monolith application to make stateless and given less timeline
- partner's repayment re-conciliation we implemented at that time but later we started getting partial payment which broke backend
- underestimating  kiscore features creations for ds team and given timeline based on one partner only since it was mentioned that data will be same format

### 4) **Tell me about a time when you discovered your idea was not the best course of action. How did you find out?**
- skipping sonata validation for ki-view due to tight timeline for first partner in sonata - failed when partial payments came
- choosing one partner for migration for kiscore feature creations and underestimating other partners - which were having more data discrepancies
-

## 11. Tell Me About a Time You Failed
- first demo to client nested json dependecies - we could show things -
- Fino bank api integration changes
- Airflow upgrade to support pyspark pipeline
- sonata-veritas data discrepancy after 7 months loan_id changes which made our pipeline and apis failed 






















### **PayPal Leadership Principles – STAR-based Responses**

#### **1. Customer First: Prioritizing customer needs to deliver exceptional experiences.**
**Situation:** At Kaleidofin, we needed to onboard key partners (e.g., Veritas, Sonata) onto Kiview Risk Infra and repayments to streamline risk assessment.  
**Task:** Ensure a seamless onboarding experience while meeting compliance and risk evaluation needs.  
**Action:** Led the integration process, collaborated with partners, and designed a risk infrastructure framework and mappers to enhance risk visibility.  
**Result:** Improved customer onboarding efficiency and strengthened risk assessment accuracy, enhancing trust with partners.

---

#### **2. Empower and Inspire: Encouraging teams to achieve their full potential.**
**Situation:** 
- While developing the Kiscore pipeline, 
- time and cost was huge and wanted to move distributed.  

**Task:** 
- Ensure clear communication,
- sign-offs, and 
- structured milestones to facilitate smooth execution. 
- multiple partners onboarding and configs support

**Action:**
- Organized requirement-gathering sessions, 
- validated needs with stakeholders, 
- drove cross-functional alignment.

**Result:** 
70% time and 65% costs. faster turnaround time. scalable system.

---

#### **3. Act with Integrity: Maintaining honesty, transparency, and ethical behavior.**
**Situation:** In fintech, data transparency and security are critical. Business teams required accurate risk and financial data for decision-making.  
**Task:** Ensure that all risk and financial data shared with stakeholders was accurate, traceable, and secure.  
**Action:** Established robust data governance practices, ensured compliance with financial regulations, and maintained transparency with teams.  
**Result:** Strengthened trust among stakeholders, enabling informed decision-making while maintaining regulatory compliance.

---

#### **4. Collaborate and Communicate: Fostering teamwork and cross-functional alignment.**
**Situation:** 
 - Kiview for  Federal Bank had a ₹3000 crore loan portfolio requiring deep insights for business correspondents (BCs) via .
 - Kiscore migration 
**Task:** Gather requirements from multiple business heads and ensure alignment among product, engineering, and business teams.  
**Action:**
- Facilitated collaboration between various stakeholders to define project milestones.
- Helped the team understand complex requirements and created structured design documents.
- Led the documentation process for milestone execution and tracking.  
  **Result:** Successfully delivered deep insights to BCs, streamlining portfolio analysis and improving loan portfolio management.

---

#### **5. Deliver Results: Achieving business impact and efficiency.**
**Situation:** 
 - Payment services (Kiscore-Reg) required integration of online and offline installment repayments for customer loans.  
 - Rupeek infra
 - Risk-pipeline (kiview) - federal bank (granular system for loan analysis)
**Task:** Reduce reconciliation efforts and onboard smaller Microfinance Institutions (MFIs) to facilitate payments.  
**Action:**
- Integrated digital and offline repayment methods to streamline transactions.
- Optimized the reconciliation process, reducing inefficiencies.  
  **Result:** Reduced payment reconciliation time by **40%**, onboarded small MFIs, and significantly cut down their collection costs.

---

#### **6. Be Agile and Adaptable: Responding to evolving business and technology needs.**
**Situation:** Rupeek needed infrastructure migrations with security fixes to make the system environment-agnostic.  
**Task:** Ensure scalable, secure, and resilient cloud infrastructure.  
**Action:**
- Migrated core services to AWS with Kubernetes, Docker, and Vault-based security.
- Implemented CI/CD pipelines for automated deployments and security fixes.  
  **Result:** Enabled **scalable deployments, improved security compliance, and reduced downtime** in production environments.

---

#### **7. Lead with Vision: Driving innovation and long-term impact.**
**Situation:** Needed to scale a small POC (PySpark-Cassandra) into a full-fledged **big data pipeline** for Kiview.  
**Task:** Create a scalable data pipeline to process partner data efficiently and support ML feature engineering.  
**Action:**
- Designed and implemented a **robust data pipeline** that processes partner data efficiently.
- Scaled the pipeline to handle **100-200GB of biweekly data**, generating ML features at scale.  
  **Result:**
- Reduced feature engineering time by **70%**, significantly accelerating model development.
- Successfully onboarded **7+ partners**, enabling seamless data ingestion and analytics at scale.



----
go through the guide 
how you drive results -> time, complexity, efficiency
communication -> clear communication, sign-offs, structured milestones, team alignment,
grind hard -> work hard, deliver results, be agile, be adaptive, new learning, lead with vision
resolving conflicts -> clear communication, team alignment, compromised solution
embrace ambiguity -> pivot, prioritise , new things to learn, lead with vision

- most challenging project 
- disagreee team and make compromise
- most difficult project and handle conflict, made success out of it
- external collaboration with team 
- disagreement and compromise
- how to take negative feedback and questions
- your received actionable feedback from peers, manager
- mentorship and guidance to juniors
- worked with diffiecult teams and members
- pivot in the project due to requirements change
- not having complete requirements to make decision


---
system design 
- clearify requirements with diagrams
- problem navigation , best practices, find trade offs
- pros and cons


