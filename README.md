# Smart Healthcare Appointment System

A comprehensive healthcare management system that allows patients to book, reschedule, and cancel doctor appointments, and securely view their medical history.

## 🏗️ Architecture

This is a **modular Maven project** with the following structure:

- **healthcare-api**: Spring Boot REST API backend
- **healthcare-common**: Shared entities and common utilities
- **healthcare-frontend**: React frontend application

## 🛠️ Tech Stack

### Backend
- **Spring Boot 3.2.0**: Core framework
- **Spring Security 6.2.0**: Authentication and authorization
- **Spring Data MongoDB**: Database integration
- **MongoDB**: NoSQL database
- **JWT (JJWT 0.12.3)**: Token-based authentication
- **Maven**: Build and dependency management

### Frontend
- **React 18.2.0**: UI framework
- **React Router DOM 6.21.0**: Routing
- **Axios 1.6.2**: HTTP client
- **Tailwind CSS 3.4.0**: Styling

## 📋 Features

### Patient Features
- ✅ User registration and authentication
- ✅ Book doctor appointments
- ✅ Reschedule appointments
- ✅ Cancel appointments
- ✅ View appointment history
- ✅ View medical history securely
- ✅ JWT-based secure sessions

### Doctor Features
- ✅ Doctor registration
- ✅ View scheduled appointments
- ✅ Manage appointments
- ✅ Create medical history records
- ✅ Update patient records

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MongoDB 4.0+ (running on localhost:27017)
- Node.js 14+ and npm
- Git

### Installation

#### 1. Clone the Repository
```bash
git clone <repository-url>
cd health-appointment
```

#### 2. Start MongoDB
Make sure MongoDB is running on your system:
```bash
# On Windows
mongod

# On Linux/Mac
sudo systemctl start mongod
# or
brew services start mongodb-community
```

#### 3. Build and Run Backend
```bash
# Navigate to project root
cd health-appointment

# Build the project
mvn clean install

# Run the Spring Boot application
cd healthcare-api
mvn spring-boot:run
```

The API will be available at: `http://localhost:8080`

#### 4. Install and Run Frontend
```bash
# Navigate to frontend directory
cd healthcare-frontend

# Install dependencies
npm install

# Start the development server
npm start
```

The frontend will be available at: `http://localhost:3000`

## 📡 API Endpoints

### Authentication
- `POST /api/auth/register/patient` - Patient registration
- `POST /api/auth/register/doctor` - Doctor registration
- `POST /api/auth/login` - User login

### Appointments
- `POST /api/appointments/book` - Book new appointment
- `GET /api/appointments/patient/{patientId}` - Get patient appointments
- `GET /api/appointments/doctor/{doctorId}` - Get doctor appointments
- `GET /api/appointments/{id}` - Get appointment details
- `PUT /api/appointments/{id}/reschedule` - Reschedule appointment
- `DELETE /api/appointments/{id}/cancel` - Cancel appointment

### Medical History
- `GET /api/medical-history/patient/{patientId}` - Get patient medical history
- `POST /api/medical-history/create` - Create medical record (Doctor only)
- `GET /api/medical-history/{id}` - Get specific medical record
- `PUT /api/medical-history/{id}` - Update medical record (Doctor only)

## 🔐 Security

- JWT-based authentication
- Password encryption using BCrypt
- Role-based access control (PATIENT, DOCTOR)
- Protected endpoints requiring authentication
- CORS enabled for frontend communication

## 📊 Database Schema

### Collections
- **patients**: Patient information
- **doctors**: Doctor information
- **appointments**: Appointment scheduling
- **medical_history**: Patient medical records

## 🧪 Testing

### Backend
```bash
cd healthcare-api
mvn test
```

### Frontend
```bash
cd healthcare-frontend
npm test
```

## 📦 Project Structure

```
health-appointment/
├── healthcare-api/
│   ├── src/main/java/com/healthcare/api/
│   │   ├── config/           # Configuration classes
│   │   ├── controller/      # REST controllers
│   │   ├── filter/          # JWT filter
│   │   ├── repository/       # Data repositories
│   │   ├── service/          # Business logic
│   │   └── util/             # Utilities
│   └── src/main/resources/
│       └── application.yml  # Application configuration
├── healthcare-common/
│   └── src/main/java/com/healthcare/common/
│       └── entity/           # Entity classes
├── healthcare-frontend/
│   ├── public/
│   ├── src/
│   │   ├── components/       # React components
│   │   ├── context/          # Context providers
│   │   ├── pages/            # Page components
│   │   └── services/         # API services
│   └── package.json
└── pom.xml                    # Parent POM
```

## 🎯 Usage Examples

### Register as Patient
```bash
curl -X POST http://localhost:8080/api/auth/register/patient \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "password123",
    "email": "john@example.com",
    "fullName": "John Doe",
    "phoneNumber": "+1234567890"
  }'
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "password123"
  }'
```

### Book Appointment
```bash
curl -X POST "http://localhost:8080/api/appointments/book?patientId=xxx&doctorId=yyy&appointmentDateTime=2024-01-15T10:00&reason=Checkup" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🔧 Configuration

### Backend Configuration (`application.yml`)
```yaml
server:
  port: 8080

spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/healthcare_appointments

jwt:
  secret: YourSecretKey
  expiration: 86400000  # 24 hours
```

## 🐛 Troubleshooting

### MongoDB Connection Issues
- Ensure MongoDB is running: `mongod` or check service status
- Verify connection string in `application.yml`

### Port Conflicts
- Backend default: 8080
- Frontend default: 3000
- Change ports in configuration files if needed

### Build Issues
- Clean and rebuild: `mvn clean install`
- Clear npm cache: `npm cache clean --force`

## 📝 License

This project is licensed under the MIT License.

## 👥 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📞 Support

For issues and questions, please open an issue on the repository.

---

Built with ❤️ using Spring Boot and React


