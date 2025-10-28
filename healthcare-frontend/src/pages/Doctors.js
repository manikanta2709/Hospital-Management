import React, { useState, useEffect } from 'react';
import api from '../services/api';
import { Link } from 'react-router-dom';

const Doctors = () => {
  const [doctors, setDoctors] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchDoctors();
  }, []);

  const fetchDoctors = async () => {
    // In a real app, you'd fetch doctors from the API
    setLoading(false);
  };

  if (loading) {
    return (
      <div className="flex items-center justify-center min-h-screen">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-primary-600"></div>
      </div>
    );
  }

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900">Available Doctors</h1>
        <p className="mt-2 text-sm text-gray-600">Browse our healthcare professionals</p>
      </div>

      <div className="bg-white rounded-lg shadow p-12 text-center">
        <p className="text-gray-500 text-lg mb-6">No doctors available at the moment</p>
        <Link
          to="/book-appointment"
          className="inline-block bg-primary-600 hover:bg-primary-700 text-white font-medium py-2 px-4 rounded-md"
        >
          Schedule Appointment
        </Link>
      </div>
    </div>
  );
};

export default Doctors;


