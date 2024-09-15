import heapq

class Solution:
    def scheduleCourse(self, courses):
        # Create a max heap to store the durations of courses (negated for max heap behavior)
        max_heap = []
        # Sort the courses by their end times in ascending order
        courses.sort(key=lambda course: course[1])
        total = 0  # Initialize a variable to keep track of the total duration of selected courses

        for course in courses:
            # Push the negated duration of the course onto the max heap
            heapq.heappush(max_heap, -course[0])
            total += course[0]  # Update the total duration

            # If the total duration exceeds the end time of the current course, remove the course with the longest duration
            if total > course[1]:
                total += heapq.heappop(max_heap)  # Remove the longest course duration from the total

        # The size of the max heap represents the number of courses that can be taken
        return len(max_heap)