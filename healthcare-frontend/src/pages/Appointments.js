import React, { useState, useEffect } from 'react';
import api from '../services/api';

const Appointments = () => {
  const [appointments, setAppointments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchAppointments();
  }, []);

  const fetchAppointments = async () => {
    try {
      // In a real app, you'd get the patientId from the authenticated user
      // For now, we'll use a hardcoded value or fetch from a patient ID
      setLoading(false);
    } catch (err) {
      setError('Failed to fetch appointments');
      setLoading(false);
    }
  };

  const cancelAppointment = async (appointmentId) => {
    try {
      await api.delete(`/appointments/${appointmentId}/cancel`);
      fetchAppointments();
    } catch (err) {
      setError('Failed to cancel appointment');
    }
  };

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleString();
  };

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900">My Appointments</h1>
        <p className="mt-2 text-sm text-gray-600">Manage your scheduled appointments</p>
      </div>

      {loading ? (
        <div className="flex items-center justify-center py-12">
          <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-primary-600"></div>
        </div>
      ) : error ? (
        <div className="bg-red-50 border border-red-200 rounded-md p-4">
          <p className="text-red-800">{error}</p>
        </div>
      ) : appointments.length === 0 ? (
        <div className="bg-white rounded-lg shadow p-12 text-center">
          <p className="text-gray-500 text-lg">No appointments scheduled</p>
          <a
            href="/book-appointment"
            className="mt-4 inline-block bg-primary-600 hover:bg-primary-700 text-white font-medium py-2 px-4 rounded-md"
          >
            Book Your First Appointment
          </a>
        </div>
      ) : (
        <div className="grid gap-6">
          {appointments.map((appointment) => (
            <div key={appointment.id} className="bg-white shadow rounded-lg overflow-hidden">
              <div className="p-6">
                <div className="flex items-center justify-between">
                  <div>
                    <h3 className="text-lg font-semibold text-gray-900">
                      Dr. {appointment.doctor?.fullName || 'Doctor Name'}
                    </h3>
                    <p className="text-sm text-gray-600 mt-1">{appointment.doctor?.specialization}</p>
                  </div>
                  <span
                    className={`px-3 py-1 rounded-full text-sm font-medium ${
                      appointment.status === 'CONFIRMED'
                        ? 'bg-green-100 text-green-800'
                        : appointment.status === 'CANCELLED'
                        ? 'bg-red-100 text-red-800'
                        : 'bg-yellow-100 text-yellow-800'
                    }`}
                  >
                    {appointment.status}
                  </span>
                </div>
                <div className="mt-4">
                  <div className="text-sm text-gray-600">
                    <span className="font-medium">Date & Time:</span> {formatDate(appointment.appointmentDateTime)}
                  </div>
                  <div className="text-sm text-gray-600 mt-1">
                    <span className="font-medium">Reason:</span> {appointment.reason}
                  </div>
                </div>
                <div className="mt-4 flex space-x-4">
                  <button
                    onClick={() => cancelAppointment(appointment.id)}
                    disabled={appointment.status === 'CANCELLED' || appointment.status === 'COMPLETED'}
                    className="bg-red-500 hover:bg-red-600 disabled:bg-gray-300 text-white font-medium py-2 px-4 rounded-md"
                  >
                    Cancel
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Appointments;


