CREATE TABLE users (
    userId INT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
);

CREATE TABLE tasks (
    taskId INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    dueDate DATE,
    completionDate DATE,
    status ENUM('TODO', 'IN_PROGRESS', 'COMPLETED') DEFAULT 'TODO',
    progressPercentage VARCHAR(255);
    priority ENUM('HIGH','MEDIUM','LOW') DEFAULT 'LOW'
);

CREATE TABLE task_assignments (
    assignment_id INT PRIMARY KEY,
    user_id INT,
    task_id INT,
    FOREIGN KEY (user_id) REFERENCES users(userId),
    FOREIGN KEY (task_id) REFERENCES tasks(taskId)
);
