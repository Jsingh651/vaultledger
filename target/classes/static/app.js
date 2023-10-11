const fmt = (n) => '$' + Number(n).toFixed(2);

async function loadDashboard() {
  const [dash, txs] = await Promise.all([
    fetch('/api/dashboard').then(r => r.json()),
    fetch('/api/transactions').then(r => r.json()),
  ]);

  document.getElementById('total-income').textContent = fmt(dash.totalIncome);
  document.getElementById('total-expenses').textContent = fmt(dash.totalExpenses);
  document.getElementById('balance').textContent = fmt(dash.balance);

  const tbody = document.querySelector('#transactions-table tbody');
  tbody.innerHTML = txs
    .sort((a, b) => b.date.localeCompare(a.date))
    .map(t => `
      <tr>
        <td>${t.date}</td>
        <td>${t.description}</td>
        <td>${t.category}</td>
        <td class="type-${t.type.toLowerCase()}">${t.type === 'INCOME' ? '+' : '-'}${fmt(t.amount)}</td>
        <td><button class="btn-delete" onclick="deleteTx(${t.id})">×</button></td>
      </tr>
    `).join('');

  const budgetEl = document.getElementById('budget-bars');
  budgetEl.innerHTML = (dash.budgetStatus || []).map(b => {
    const pct = Math.min(b.percentUsed, 100);
    const cls = b.percentUsed > 100 ? 'over' : b.percentUsed > 80 ? 'warn' : 'ok';
    return `
      <div class="budget-item">
        <div class="budget-header">
          <span>${b.category}</span>
          <span>${fmt(b.spent)} / ${fmt(b.limit)}</span>
        </div>
        <div class="budget-bar">
          <div class="budget-fill ${cls}" style="width:${pct}%"></div>
        </div>
      </div>
    `;
  }).join('') || '<p style="color:var(--muted)">No budgets set</p>';

  const cats = dash.expensesByCategory || {};
  const maxVal = Math.max(...Object.values(cats), 1);
  const chartEl = document.getElementById('category-chart');
  chartEl.innerHTML = Object.entries(cats).map(([name, val]) => `
    <div class="cat-bar">
      <span class="cat-name">${name}</span>
      <div class="cat-track">
        <div class="cat-fill" style="width:${(val / maxVal) * 100}%"></div>
      </div>
      <span class="cat-val">${fmt(val)}</span>
    </div>
  `).join('') || '<p style="color:var(--muted)">No expenses yet</p>';
}

async function deleteTx(id) {
  await fetch(`/api/transactions/${id}`, { method: 'DELETE' });
  loadDashboard();
}

function openModal() {
  document.getElementById('modal').classList.add('open');
  document.querySelector('[name="date"]').value = new Date().toISOString().split('T')[0];
}

function closeModal() {
  document.getElementById('modal').classList.remove('open');
}

document.getElementById('tx-form').addEventListener('submit', async (e) => {
  e.preventDefault();
  const fd = new FormData(e.target);
  await fetch('/api/transactions', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      description: fd.get('description'),
      amount: parseFloat(fd.get('amount')),
      type: fd.get('type'),
      category: fd.get('category'),
      date: fd.get('date'),
    }),
  });
  e.target.reset();
  closeModal();
  loadDashboard();
});

loadDashboard();
