package com.avryahov.coursesapp.data.repository

import com.avryahov.coursesapp.R
import com.avryahov.coursesapp.data.model.Course

class DefaultCourseRepository : CourseRepository {

    override suspend fun getCourses(): List<Course> = listOf(
        Course(
            id = "100",
            title = "Java-разработчик с нуля",
            text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
            price = "999",
            rate = "4.9",
            startDate = "2024-05-22",
            hasLike = false,
            publishDate = "2024-02-02",
            bannerResId = R.drawable.course_banner_1
        ),
        Course(
            id = "101",
            title = "3D-дженералист",
            text = "Освой профессию 3D-дженералиста и стань универсальным специалистом, который умеет создавать 3D-модели, текстуры и анимации, а также может строить карьеру в геймдеве, кино, рекламе или дизайне.",
            price = "12 000",
            rate = "3.9",
            startDate = "2024-09-10",
            hasLike = false,
            publishDate = "2024-01-20",
            bannerResId = R.drawable.course_banner_2
        ),
        Course(
            id = "102",
            title = "Python Advanced. Для продвинутых",
            text = "Вы узнаете, как разрабатывать гибкие и высокопроизводительные серверные приложения на языке Kotlin. Преподаватели на вебинарах покажут пример того, как разрабатывается проект маркетплейса: от идеи и постановки задачи – до конечного решения",
            price = "1 299",
            rate = "4.3",
            startDate = "2024-10-12",
            hasLike = true,
            publishDate = "2024-08-10",
            bannerResId = R.drawable.course_banner_3
        )
    )
}