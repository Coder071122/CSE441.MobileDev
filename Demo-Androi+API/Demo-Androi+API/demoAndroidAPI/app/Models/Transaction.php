<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Transaction extends Model
{
    protected $fillable = [
        'transaction_code',
        'member_code',
        'type',
        'amount',
        'status',
        'note'
    ];

    protected $casts = [
        'amount' => 'decimal:2'
    ];

    public function player()
    {
        return $this->belongsTo(Player::class, 'member_code', 'member_code');
    }
}
