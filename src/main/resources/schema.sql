CREATE TABLE "question" (
                            "id" SERIAL PRIMARY KEY,
                            "category" VARCHAR(25),
                            "difficulty_level" VARCHAR(25),
                            "option1" VARCHAR(255),
                            "option2" VARCHAR(255),
                            "option3" VARCHAR(255),
                            "option4" VARCHAR(255),
                            "question_title" VARCHAR(255),
                            "correct_answer" VARCHAR(255)
);