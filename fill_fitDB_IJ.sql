-- -----------------------------------------------------------
-- Author: Jackson O'Donnell
--
-- Purpose: Create dummy data for testing
-- -----------------------------------------------------------

connect 'jdbc:derby:.\fitDB';

INSERT INTO users VALUES
    ('Bryan', 'Bryan Lee', 'password'),
    ('Jjackal', 'Jackson ODonnell', 'password'),
    ('ConcussionBoi', 'Trenton Strickland', 'lol');

INSERT INTO diary VALUES
    ('Bryan', '20/06/2019'),
    ('Bryan', '21/06/2019'),
    ('Bryan', '22/06/2019'),
    ('Bryan', '23/06/2019'),
    ('Bryan', '24/06/2019'),
    ('Jjackal', '01/02/2019'),
    ('ConcussionBoi', '02/12/2019');

INSERT INTO food_list VALUES
    ('Bryan', '20/06/2019', 'banana', 1),
    ('Bryan', '20/06/2019', 'apple', 1),
    ('Bryan', '20/06/2019', 'strudel', 1),
    ('Bryan', '20/06/2019', 'schnitzel', 1),
    ('Bryan', '21/06/2019', 'hagelslag', 1),
    ('Bryan', '22/06/2019', 'omlette du fromage', 1),
    ('Bryan', '23/06/2019', 'strudel', 1),
    ('Bryan', '24/06/2019', 'good eatin', 1),
    ('Jjackal', '01/02/2019', 'food', 1),
    ('Jjackal', '01/02/2019', 'apple', 1),
    ('ConcussionBoi', '01/02/2019', 'apple', 1);
    
INSERT INTO food_notes VALUES
    ('Bryan', '20/06/2019', 'This is a note'),
    ('Bryan', '21/06/2019', 'note'),
    ('Bryan', '22/06/2019', 'note'),
    ('Bryan', '23/06/2019', 'note'),
    ('Bryan', '24/06/2019', 'note'),
    ('Jjackal', '01/02/2019', 'note'),
    ('ConcussionBoi', '02/12/2019', 'note');
    
INSERT INTO sleep VALUES
    ('Bryan', '20/06/2019', 1, 'a note'),
    ('Bryan', '21/06/2019', 1, 'a note'),
    ('Bryan', '22/06/2019', 1, 'a note'),
    ('Bryan', '23/06/2019', 1, 'a note'),
    ('Bryan', '24/06/2019', 1, 'a note'),
    ('Jjackal', '01/02/2019', 1, 'a note'),
    ('ConcussionBoi', '02/12/2019', 1, 'a note');
    
INSERT INTO food VALUES
    ('banana', 5, 5, 5, 5),
    ('apple', 5, 5, 5, 5),
    ('strudel', 5, 5, 5, 5),
    ('schnitzel', 5, 5, 5, 5),
    ('hagelslag', 5, 5, 5, 5),
    ('omlette du fromage', 5, 5, 5, 5),
    ('good eatin', 5, 5, 5, 5),
    ('food', 5, 5, 5, 5);
    
INSERT INTO work_out VALUES
    ('Bryan', '20/06/2019', 1, 'a note'),
    ('Bryan', '21/06/2019', 1, 'a note'),
    ('Bryan', '22/06/2019', 1, 'a note'),
    ('Bryan', '23/06/2019', 1, 'a note'),
    ('Bryan', '24/06/2019', 1, 'a note'),
    ('Jjackal', '01/02/2019', 1, 'a note'),
    ('ConcussionBoi', '02/12/2019', 1, 'a note');

INSERT INTO oneRep VALUES
    ('1.0', '1.0', '1.0', 'Bryan'),
    ('1.0', '1.0', '1.0', 'Jjackal'),
    ('1.0', '1.0', '1.0', 'ConcussionBoi');
