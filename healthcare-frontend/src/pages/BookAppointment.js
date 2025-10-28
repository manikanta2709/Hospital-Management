import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../services/api';

const BookAppointment = () => {
  const [formData, setFormData] = useState({
    doctorId: '',
    appointmentDateTime: '',
    reason: '',
    patientId: '' // In real app, this would come from logged-in user
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      await api.post('/appointments/book', null, {
        params: formData
      });
      setSuccess(true);
      setTimeout(() => {
        navigate('/appointments');
      }, 2000);
    } catch (err) {
      setError(err.response?.data || 'Failed to book appointment');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-3xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900">Book Appointment</h1>
        <p className="mt-2 text-sm text-gray-600">Schedule a visit with your doctor</p>
      </div>

      {success ? (
        <div className="bg-green-50 border border-green-200 rounded-md p-8 text-center">
          <div className="text-green-600 text-4xl mb-4">✅</div>
          <p className="text-green-800 text-lg font-medium">Appointment booked successfully!</p>
          <p className="text-green-600 mt-2">Redirecting to appointments...</p>
        </div>
      ) : (
        <form onSubmit={handleSubmit} className="bg-white shadow rounded-lg p-8">
          {error && (
            <div className="mb-6 bg-red-50 border border-red-200 rounded-md p-4">
              <p className="text-red-800">{error}</p>
            </div>
          )}

          <div className="space-y-6">
            <div>
              <label htmlFor="patientId" className="block text-sm font-medium text-gray-700">
                Patient ID
              </label>
              <input
                id="patientId"
                name="patientId"
                type="text"
                required
                className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500"
                value={formData.patientId}
                onChange={handleChange}
              />
            </div>

            <div>
              <label htmlFor="doctorId" className="block text-sm font-medium text-gray-700">
                Doctor ID
              </label>
              <input
                id="doctorId"
                name="doctorId"
                type="text"
                required
                className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500"
                value={formData.doctorId}
                onChange={handleChange}
                placeholder="Enter Doctor ID"
              />
            </div>

            <div>
              <label htmlFor="appointmentDateTime" className="block text-sm font-medium text-gray-700">
                Date & Time
              </label>
              <input
                id="appointmentDateTime"
                name="appointmentDateTime"
                type="datetime-local"
                required
                className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500"
                value={formData.appointmentDateTime}
                onChange={handleChange}
              />
            </div>

            <div>
              <label htmlFor="reason" className="block text-sm font-medium text-gray-700">
                Reason for Visit
              </label>
              <textarea
                id="reason"
                name="reason"
                rows="4"
                required
                className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500"
                value={formData.reason}
                onChange={handleChange}
                placeholder="Describe your symptoms or reason for visit..."
              />
            </div>
          </div>

          <div className="mt-8 flex space-x-4">
            <button
              type="submit"
              disabled={loading}
              className="flex-1 bg-primary-600 hover:bg-primary-700 text-white font-medium py-2 px-4 rounded-md disabled:opacity-50"
            >
              {loading ? 'Booking...' : 'Book Appointment'}
            </button>
            <button
              type="button"
              onClick={() => navigate('/dashboard')}
              className="flex-1 bg-gray-200 hover:bg-gray-300 text-gray-800 font-medium py-2 px-4 rounded-md"
            >
              Cancel
            </button>
          </div>
        </form>
      )}
    </div>
  );
};

export default BookAppointment;


