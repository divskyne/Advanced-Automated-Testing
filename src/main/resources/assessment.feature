Feature: Testing Pet Clinic

	Scenario: User Story 1
    Given a vet
    When I retrieve all records
    Then I can see the records available for animal with id 3
  
Scenario Outline: See relevant records
  Given a vet
  When I retrieve all records
  Then  I can see the records available for animal with id <id>
  
Examples:
  | id |
  | 1 |
  | 2 |
  | 3 | 
  | 77 |

	Scenario: User Story 2
    Given an admin
    When I update a record of id 3
    Then the correct details are now shown on id 3

	Scenario: User Story 3
    Given an admin
    When I delete a animal of id 2
    Then emails arent sent to deceased annimals
	
	Scenario: User Story 4
    Given an admin
    When I add new record for id 29
    Then the records are added
	
	Scenario: User Story 5
    Given an admin
    When I add new owners to the records of id 24
    Then the record is added