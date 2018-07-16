# Employee-Reimbursement-System
Employee Bill Reimbursement System A web application for employees to submit and track bills that they claim for reimbursement thus leading to successfull payment of bill amount.

Motivation :
1. Bill reimbursement is time consuming and tedious work.
2. Employee may not have access to traditional way of submitting bills data (excel sheet, etc ) via mail at client location all the time.
3.Easier way to track your submission status, action to be taken, etc.

Entities:
1. Approvers - To verify the validity of submitted bills by employees under him.
2. Payroll team - To monitor the approved bills and process the payment of the reimbursement amount.
3. Employee - To upload the bills in a timely manner.

Technology: 
Java at backend server - Spring boot, spring data JPA, spring hibernate 
UI/UX - Angular 2, Bootstrap, Html & CSS

Features:

1.Designing the workflow for the system that helps to efficiently implement the stages through which the order consisting of set of bills   passes before getting reimbursed.

2.Setting up an event based mechanism by establishing sending and receiving queues which continuously listens to various types of events     to change the status of an order from one state to another in real time.

3.Implementing the 'Chain Of Responsibility Design Pattern' as the client is unaware which manager in the hierarchy will approve the         bills. Main advantage is that can add any manager in the hierarchy on the fly and client is unaware who approved his request as long as   the request is approved.
