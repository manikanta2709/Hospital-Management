import React, { useState, useEffect } from 'react';
import api from '../services/api';

const MedicalHistory = () => {
  const [history, setHistory] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchMedicalHistory();
  }, []);

  const fetchMedicalHistory = async () => {
    try {
      // In a real app, you'd get the patientId from the authenticated user
      setLoading(false);
    } catch (err) {
      setError('Failed to fetch medical history');
      setLoading(false);
    }
  };

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleDateString();
  };

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900">Medical History</h1>
        <p className="mt-2 text-sm text-gray-600">View your past medical records and diagnoses</p>
      </div>

      {loading ? (
        <div className="flex items-center justify-center py-12">
          <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-primary-600"></div>
        </div>
      ) : error ? (
        <div className="bg-red-50 border border-red-200 rounded-md p-4">
          <p className="text-red-800">{error}</p>
        </div>
      ) : history.length === 0 ? (
        <div className="bg-white rounded-lg shadow p-12 text-center">
          <p className="text-gray-500 text-lg">No medical history available</p>
        </div>
      ) : (
        <div className="grid gap-6">
          {history.map((record) => (
            <div key={record.id} className="bg-white shadow rounded-lg overflow-hidden">
              <div className="p-6">
                <div className="flex items-center justify-between mb-4">
                  <h3 className="text-lg font-semibold text-gray-900">
                    Visit on {formatDate(record.visitDate)}
                  </h3>
                  <span className="text-sm text-gray-500">
                    Dr. {record.doctor?.fullName || 'Unknown Doctor'}
                  </span>
                </div>
                
                {record.diagnosis && (
                  <div className="mb-4">
                    <h4 className="text-sm font-medium text-gray-700">Diagnosis</h4>
                    <p className="text-gray-900">{record.diagnosis}</p>
                  </div>
                )}
                
                {record.chiefComplaint && (
                  <div className="mb-4">
                    <h4 className="text-sm font-medium text-gray-700">Chief Complaint</h4>
                    <p className="text-gray-900">{record.chiefComplaint}</p>
                  </div>
                )}
                
                {record.notes && (
                  <div className="mb-4">
                    <h4 className="text-sm font-medium text-gray-700">Notes</h4>
                    <p className="text-gray-900">{record.notes}</p>
                  </div>
                )}

                {record.medications && record.medications.length > 0 && (
                  <div className="mb-4">
                    <h4 className="text-sm font-medium text-gray-700">Medications</h4>
                    <ul className="list-disc list-inside text-gray-900">
                      {record.medications.map((med, idx) => (
                        <li key={idx}>{med}</li>
                      ))}
                    </ul>
                  </div>
                )}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default MedicalHistory;


